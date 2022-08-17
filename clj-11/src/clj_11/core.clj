(ns clj-11.core
  (:require [clj-11.book :as book])
  (:gen-class))

(def file-path "/Users/jtovar/Documents/personal-spikes/from-java-8-to-java-17-kotlin-clojure/clj-11/src/resources/programming-test.csv")


(defn -main
  [& args]
  (println (book/csv->json file-path)))


