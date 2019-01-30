

Consul is used to hold the externalized properties for our Spring Boot application. 

For large files, we cannot use Spring Boot’s default StandardServletMultipartResolver or CommonsMultipartResolver, since the server has limited resources (disk space) or memory for buffering. So we need to disable the default MultipartResolverand define our ownMultipartResolver, which is present in the main application class.

1. consul agent -dev -config-dir=C:\\consul_0.7.3_windows_amd64
2. Now create the yml file with configurations under the key-value section. config/spring-boot-consul.yml
          spring:
              application:
                  name: image-uploading-message-queue
              servlet:
                  multipart:
                      enabled: false
                      max-file-size: 10MB
          server:
              port: 8080







###### References:
1. https://www.javacodegeeks.com/2014/01/simple-message-queue-using-redis.html
2. https://medium.com/@jegasingamjeyanthasingam/consul-e940e796ea53
