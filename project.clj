(defproject jim "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}

  :min-lein-version "2.5.3"

  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.8.51"]
                 [devcards "0.2.1-7"]
                 [sablono "0.7.1"]
                ;  [org.clojure/core.async "0.2.385"]

                 ;; need to specify this for sablono
                 ;; when not using devcards
                 [cljsjs/react-with-addons "15.0.2-0"
                    :exclude [cljsjs/react-dom]]
                 [cljsjs/react-dom "15.0.2-0"]]


  :plugins [[lein-figwheel "0.5.6"]
            [lein-cljsbuild "1.1.3" :exclusions [org.clojure/clojure]]]

  :clean-targets ^{:protect false} ["resources/public/js/compiled"
                                    "target"]

  :source-paths ["src"]

  :cljsbuild {
              :builds [{:id           "devcards"
                        :source-paths ["src"]
                        :figwheel     {:devcards true}      ;; <- note this
                        :compiler     {:main                 "jim.core"
                                       :asset-path           "js/compiled/devcards_out"
                                       :output-to            "resources/public/js/compiled/jim_devcards.js"
                                       :output-dir           "resources/public/js/compiled/devcards_out"
                                       :source-map-timestamp true}}
                       {:id           "dev"
                        :source-paths ["src"]
                        :figwheel     true
                        :compiler     {:main                 "jim.core"
                                       :asset-path           "js/compiled/out"
                                       :output-to            "resources/public/js/compiled/jim.js"
                                       :output-dir           "resources/public/js/compiled/out"
                                       :source-map-timestamp true}}
                       {:id           "prod"
                        :source-paths ["src"]
                        :compiler     {:main          "jim.core"
                                       :asset-path    "js/compiled/out"
                                       :output-to     "resources/public/js/compiled/jim.js"
                                       :optimizations :advanced}}]}

  :figwheel {:css-dirs         ["resources/public/css"]
             :http-server-root "public"                     ;; this will be in resources/
             :server-port      3449                         ;; default is 3449
             :server-ip        "localhost"})                  ;; default is "localhost"
