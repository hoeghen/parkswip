(defproject parkswip "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [com.datomic/datomic-free "0.9.5697"]
                 [clojure.java-time "0.3.2"]
                 [provisdom/spectomic "0.7.11"]]
  :jvm-opts ["-Xmx1g"]

  :main ^:skip-aot parkswip.core
  :target-path "target/%s"
  :profiles {
             :dev {:dependencies [[org.clojure/test.check "0.9.0"]]}
             :uberjar {:aot :all}})
