(ns clj-11.book
  (:require [clojure.string :as str])
  (:import java.math.RoundingMode)
  )

(defn- read-csv [path]
  ""
  (str/replace (slurp path) #"\n" ""))

(defn- karma-by-category [category]
  ""
  (category {:fiction       25
             :programming   40
             :psychological 30}))

(defn- is-ebook? [category]
  ""
  (category {:fiction       false
             :programming   true
             :psychological false}))

(defn- is-number? [element]
  ""
  (try
    (number? (bigdec element))
    (catch Exception _
      false))
  )

(defn- average [coll]
  ""
  (let [sum (reduce + coll)
        count (count coll)]
    (.divide sum (bigdec count) 2 RoundingMode/HALF_UP))
  )

(defn- rate [content-list]
  ""
  (let [all-numbers (map #(bigdec %) (filter is-number? content-list))
        valid-rates (filter #(<= 1 % 5) all-numbers)]
    (average valid-rates)
    ))

(defn- create-json-record [content-list]
  ""
  (let [title (nth content-list 0)
        author (nth content-list 1)
        category (keyword (nth content-list 3))
        pages (Integer/parseInt (nth content-list 2))
        karma (karma-by-category category)
        rate (rate content-list)
        ebook (is-ebook? category)]
    {:title    title
     :author   author
     :pages    pages
     :category category
     :karma    karma
     :rate     rate
     :ebook    ebook
     }))

(defn csv->json [file-path]
  ""
  (let [csv-content (read-csv file-path)
        content-list (str/split csv-content #",")
        content (create-json-record content-list)]
    content))

