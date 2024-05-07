(ns datasource
  (:require [com.stuartsierra.component :as component])
  (:import  [com.zaxxer.hikari HikariConfig HikariDataSource]))

(defn hikari-config [config]
  (let [{:keys [jdbc-url user password]} config]
    (doto (HikariConfig.)
      (.setJdbcUrl jdbc-url)
      (.setUsername user)
      (.setPassword password))))

(defrecord Datasource [config]
  component/Lifecycle
  (start [this]
    (let [datasource (HikariDataSource. (hikari-config config))]
      (merge this {:datasource datasource
                   :jdbc datasource})))
  (stop [{:keys [datasource]}]
    (.close datasource)))