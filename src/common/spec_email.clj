(ns common.spec-email
  (:require [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as gen]))

(def email-regex #"^\S+@\S+\.\S+$")

(def non-empty-string-alphanumeric
  "Generator for non-empty alphanumeric strings"
  (gen/such-that #(not= "" %)
                 (gen/string-alphanumeric)))


(def email-gen
  "Generator for email addresses"
  (gen/fmap
    (fn [[name host tld]]
      (str name "@" host "." tld))
    (gen/tuple
      non-empty-string-alphanumeric
      non-empty-string-alphanumeric
      non-empty-string-alphanumeric)))

(s/def ::email (s/with-gen
                 (s/and string? #(re-matches email-regex %))
                 (fn [] email-gen)))



