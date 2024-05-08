(ns app
  (:require [system   :as system]
            [clojure.java.io       :as io]
            [clojure.tools.logging :as log]))

(defn read-config []
  (->> (io/resource "config.edn")
       slurp
       read-string))

(defn -main
  [& _]
  (let [config (read-config)]
    (log/info "Server starting...")
    (.start (system/build-server config)))
    (log/info "Server started."))

