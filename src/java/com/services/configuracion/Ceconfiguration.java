package com.services.configuracion;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @descripcion: Clase que permite configurar los paquetes a ser utilizados para la configuración del springframework. 
 * @autor: (Rodolfo Cárcamo) 
 * @fechaCreacion: 12-04-2017
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.services")
public class Ceconfiguration {

}
