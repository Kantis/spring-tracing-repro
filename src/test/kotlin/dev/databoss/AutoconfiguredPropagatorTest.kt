package dev.databoss

import io.kotest.core.spec.style.FunSpec
import io.kotest.inspectors.forOne
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.opentelemetry.context.propagation.ContextPropagators
import io.opentelemetry.context.propagation.TextMapPropagator
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext

@SpringBootTest(properties = ["management.tracing.enabled=true"])
class AutoconfiguredPropagatorTest(
   val applicationContext: ApplicationContext,
) : FunSpec(
   {
      test("Application context should have management.tracing.enabled = true"){
         applicationContext.environment.getProperty("management.tracing.enabled") shouldBe "true"
      }

      test("The no-op propagator should not be used") {
         val propagators = applicationContext.getBeansOfType(ContextPropagators::class.java)
         propagators.forOne {
            it.key shouldBe "otelContextPropagators"
            it.value.textMapPropagator::class.java.name shouldNotBe
               "io.opentelemetry.context.propagation.NoopTextMapPropagator"
         }
      }

      test("The CompositeTextMapPropagator should be used") {
         val propagators = applicationContext.getBeansOfType(ContextPropagators::class.java)
         propagators.forOne {
            it.key shouldBe "otelContextPropagators"
            it.value.textMapPropagator::class.java.name shouldBe
               "org.springframework.boot.actuate.autoconfigure.tracing.CompositeTextMapPropagator"
         }
      }

      test("B3 multi-propagator should be used") {
         val textMapPropagators = applicationContext.getBeansOfType(TextMapPropagator::class.java)
         println(textMapPropagators)
      }
   },
)
