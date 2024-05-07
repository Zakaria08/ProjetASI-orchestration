(ns app
  (:require [system   :as system]
            [clojure.java.io       :as io]
            [clojure.tools.logging :as log]))

(defn read-config
  []
  (->> (io/resource "config.edn")
       slurp
       read-string))

(defn run
  [& _]
  (let [config (read-config)]
    (log/info "Server starting...")
    (.start (system/build-server config)))
    (log/info "Server started."))

(comment
  (run))

(comment
  (use 'com.stuartsierra.component)
  (def system (.start (system/build-server (read-config))))
  (stop system))