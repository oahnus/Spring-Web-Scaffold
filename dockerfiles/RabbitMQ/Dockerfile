FROM rabbitmq

# 开启 延迟队列 插件
ADD rabbitmq_delayed_message_exchange-0.0.1.ez $RABBITMQ_HOME/plugins/

RUN rabbitmq-plugins enable --offline rabbitmq_management

RUN rabbitmq-plugins enable --offline rabbitmq_delayed_message_exchange

EXPOSE 15671 15672