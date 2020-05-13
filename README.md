# boot-gmall

前台管理系统：8888
#boot-gmall-user 服务端口：8080
dubbo-admin 端口：8080
boot-gmall-user-service: 8010
boot-gmall-user-web：8011

boot-gmall-manager-web：8070
boot-gmall-manager-service：8071

boot-gmall-item-web: 8072

boot-gmall-search-web: 8073
boot-gmall-search-service: 8074





1 首页（静态化）
2 检索页（搜索引擎）
3 详情页（缓存、切换、推荐）
4 购物车页（cookie、redis）使不使用session取决于项目的规模，规模小就使用session共享，更简单
5 结算页(订单页)（一致性校验、安全）
6 支付页（安全、对接支付平台）