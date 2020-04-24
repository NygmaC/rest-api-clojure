(defproject rest-app "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.0"]
            ; Compojure - A basic routing library
            [compojure "1.6.1"]
            ; Our Http library for client/server
            [http-kit "2.3.0"]
            ; Ring defaults - for query params etc
            [ring/ring-defaults "0.3.2"]
            ; Clojure data.JSON library
            [org.clojure/data.json "0.2.6"]]
  :main ^:skip-aot rest-api-clojure.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
