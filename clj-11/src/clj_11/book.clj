(ns clj-11.book
    (:require [clojure.string :as str])
    (:import java.math.RoundingMode)
    )

(defn- read-csv [path]
       "Read file as string and remove breaking lines"
       (str/replace (slurp path) #"\n" ""))

(defn- karma-by-category [category]
       "Get the karma based on book category"
       (category {:fiction       25
                  :programming   40
                  :psychological 30}))

(defn- is-ebook? [category]
       "Get the kind of book based on book category"
       (category {:fiction       false
                  :programming   true
                  :psychological false}))

(defn- is-number? [element]
       "Try to cast the element to a bigDecimal to identify the element as a valid number"
       (try
         (number? (bigdec element))
         (catch Exception _
           false))
       )

(defn- average [coll]
       "Get all the valid rates and cast to bigDecimal"
       (let [sum (reduce + coll)
             count (count coll)]
            (.divide sum (bigdec count) 2 RoundingMode/HALF_UP))
       )

(defn- rate [content-list]
       "Get the average rate from numbers raging between 1 and 5 "
       (let [all-numbers (map #(bigdec %) (filter is-number? content-list))
             valid-rates (filter #(<= 1 % 5) all-numbers)]
            (average valid-rates)
            ))

(defn- create-json-record [content-list]
       "map to a edn using the list index"
       (let [[title author pages c] content-list
             category (keyword c)
             karma (karma-by-category category)
             rate (rate content-list)
             ebook (is-ebook? category)]
            {:title    title
             :author   author
             :pages    (Integer/parseInt pages)
             :category category
             :karma    karma
             :rate     rate
             :ebook    ebook
             }))

(defn csv->json [file-path]
      "Read the document and convert it to a valid EDN"
      (let [csv-content (read-csv file-path)
            content-list (str/split csv-content #",")
            content (create-json-record content-list)]
           content))

