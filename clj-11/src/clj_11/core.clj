(ns clj-11.core
  (:require [clj-11.book :as book]
            [clojure.java.io :as io]
            )
  (:gen-class))

(def file-path (io/resource "resources/programming-test.csv"))


(defn -main
  [& args]
  (println (book/csv->json file-path)))


