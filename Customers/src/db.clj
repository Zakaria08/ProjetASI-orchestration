(ns db
  (:require [next.jdbc            :as jdbc]
            [next.jdbc.result-set :as rs]))

(defn find-all-customers [datasource]
  (jdbc/execute! datasource
                 ["SELECT lastname,
                          firstname,
                          gender,
                          DATE_FORMAT(CONVERT_TZ(birthday, '+00:00', @@session.time_zone), '%d-%m-%Y') AS birthday,
                          cardnumber
                   FROM customers"]
                 {:builder-fn rs/as-lower-maps}))

(defn find-customer-by-cardnumber [datasource cardnumber]
  (jdbc/execute! datasource
                 ["SELECT lastname,
                          firstname,
                          gender,
                          DATE_FORMAT(CONVERT_TZ(birthday, '+00:00', @@session.time_zone), '%d-%m-%Y') AS birthday,
                          cardnumber
                   FROM customers
                   WHERE cardnumber = ?" cardnumber]
                 {:builder-fn rs/as-lower-maps}))