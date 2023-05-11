package com.akshay.blog.config;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
@AutoConfigureBefore({JacksonAutoConfiguration.class})
public class DateTimeAutoConfiguration {

  @Bean
  public Jackson2ObjectMapperBuilderCustomizer jacksonObjectMapperBuilderCustomizer() {
    return new Jackson2ObjectMapperBuilderCustomizer() {

      @Override
      public void customize(Jackson2ObjectMapperBuilder builder) {
        builder.serializerByType(LocalDateTime.class, new JsonSerializer<LocalDateTime>() {
          @Override
          public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers)
                  throws IOException, JsonProcessingException {
            gen.writeString(value.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
          }
        });
      }

    };
  }
}

