(ns clj-11.core-test
  (:require [clj-11.book :as book]
            [clj-11.core :refer :all]
            [clojure.test :refer :all]))

(deftest test-csv-json-with-valid-file

  (testing "Generate EDN based on file"
    (is (= {:author   "Steve McConnell"
            :category :programming
            :ebook    true
            :karma    40
            :pages    960
            :rate     3.83M
            :title    "Code Complete"} (book/csv->json file-path)))))
