# CurrentLimit

1.配置文件
#时间窗口，即一个单位时间
window.limiter.timeWindow=1000
#限流数 即单位时间内允许访问通过的最大数
window.limiter.count=10

2.启动
3.访问 127.0.0.1:9293/v1/cese    127.0.0.1:9293/v1/cese2 均可
4.自定义接口添加 @SlideWindowLimit 注解即可实现限流

