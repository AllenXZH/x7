/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package x7;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import x7.config.DateToLongSerializer;
import x7.config.SpringHelper;

import java.util.Date;
import java.util.Objects;


@Configuration
public class BootConfiguration {

    @Autowired
    private Environment environment;


    @Bean
    @Order(1)
    @ConditionalOnMissingBean(SpringHelper.class)
    public SpringHelper enableHelper(){
        return new SpringHelper();
    }

    @Bean
    @Order(2)
    public X7Config enableX7Config() {

        X7Env env = SpringHelper.getObject(X7Env.class);

        if (Objects.nonNull(env))
            return new X7Config();

        new X7ConfigStarter(environment);

        return new X7Config();
    }


}
