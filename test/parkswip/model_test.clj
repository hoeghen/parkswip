(ns parkswip.model-test
  (:require [clojure.test :refer :all]
            [parkswip.model :refer :all]
            [clojure.spec.gen.alpha :as gen]))


(deftest model-test
  (testing "generating test data"
    (is (= 1 1))))
