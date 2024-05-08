(ns service
  (:require [db :as db]))

(defprotocol ICustomerService
  (find-all-customers [this])
  (find-customer-by-cardnumber [this cardnumber]))

(defrecord CustomerService [datasource]
  ICustomerService
  (find-all-customers [_]
    (db/find-all-customers datasource))
  (find-customer-by-cardnumber [_ cardnumber]
    (db/find-customer-by-cardnumber datasource cardnumber)))