(ns common.spec-time
  (:require [clojure.spec.alpha :as s]
            [java-time :as t]
            [clojure.spec.gen.alpha :as gen])
  (:import (java.time LocalTime)))


; local-time
;; define a time tupple generator
(def tuple-time-gen (s/gen (s/tuple (s/int-in 0 23)
                                    (s/int-in 0 59)
                                    (s/int-in 0 59))))
;; define a partial that creates local-time object from tuple
(def create-time (partial apply t/local-time))

;; define a time geneator that creates local-time objects
(def time-gen (gen/fmap create-time
                        tuple-time-gen))

;; define time? predicate
(defn time? [var]
  (instance? LocalTime var))


;; define ::time spec
(s/def ::time (s/with-gen
                time?
                (fn [] time-gen)))



