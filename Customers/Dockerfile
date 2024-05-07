FROM clojure:openjdk-11-tools-deps

WORKDIR /usr/src/app

COPY deps.edn .

RUN clojure -P

COPY src ./src
COPY resources ./resources

EXPOSE 11000

CMD ["clojure", "-m", "app"]
