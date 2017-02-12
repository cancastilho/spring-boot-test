package br.com.cancastilho.config;

/*
 * Configuração necessária para usar Pageable como argumento nos métodos dos
 * controladores.
 */

// @Configuration // o uso de @EnableSpringDataWebSupport já provê uma
// implementação padrão.
// public class PagingConfig extends WebMvcConfigurationSupport {
//
// @Override
// public void addArgumentResolvers(List<HandlerMethodArgumentResolver>
// argumentResolvers) {
// PageableHandlerMethodArgumentResolver resolver = new
// PageableHandlerMethodArgumentResolver();
// resolver.setFallbackPageable(new PageRequest(0, 50));
// argumentResolvers.add(resolver);
// super.addArgumentResolvers(argumentResolvers);
// }
//
// }