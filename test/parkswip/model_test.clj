(ns parkswip.model-test
  (:require [clojure.test :refer :all]
            [parkswip.model :as m]
            [parkswip.dao :as dao]
            [clojure.test.check.clojure-test :refer [defspec]]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]))




(def save-user-test
  (prop/for-all [user m/user-generator]
                (let [r (dao/save-user user)
                      e (:parkswip.model/email user)]
                     (= r e))))

(def save-user-and-load-test
  (prop/for-all [user m/user-generator]
                (let [r (dao/save-user user)
                      e (:parkswip.model/email user)
                      u (dao/get-user e)]
                  (= e (:parkswip.model/email u)))))


(defspec save-user 100 save-user-test)
(defspec save-user-load-user 100 save-user-and-load-test)







