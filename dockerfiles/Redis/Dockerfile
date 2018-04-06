FROM redis

VOLUME /data

ENV REDIS_PASSWORD root

CMD ["sh", "-c", "exec redis-server --requirepass \"$REDIS_PASSWORD\""]

EXPOSE 6379