(ns db
  (:require [next.jdbc            :as jdbc]
            [next.jdbc.result-set :as rs]))

(defn find-all-customers [datasource]
  (jdbc/execute! datasource
                 ["SELECT lastname,
                          firstname,
                          gender,
                          birthday,
                          cardnumber
                   FROM customers"]
                 {:builder-fn rs/as-lower-maps}))

(defn find-customer-by-cardnumber [datasource cardnumber]
  (jdbc/execute! datasource
                 ["SELECT lastname,
                          firstname,
                          gender,
                          birthday,
                          cardnumber
                   FROM customers
                   WHERE cardnumber = ?" cardnumber]
                 {:builder-fn rs/as-lower-maps}))