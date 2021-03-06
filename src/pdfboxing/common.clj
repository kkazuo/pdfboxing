(ns pdfboxing.common
  (:import
   [javax.activation FileDataSource]
   [org.apache.pdfbox.pdmodel PDDocument]
   [org.apache.pdfbox.preflight.parser PreflightParser]))


(defn is-pdf?
  "Confirm that the PDF supplied is really a PDF"
  [pdf-file]
  (let [data-source (FileDataSource. pdf-file)
        parser (PreflightParser. data-source)]
    (try
      (do
        (.parse parser)
        true)
      (catch Exception e false))))


(defn load-pdf
  "Load a given PDF only after checking if it really is a PDF"
  [pdf-file]
  (if (is-pdf? pdf-file)
    (PDDocument/load pdf-file)
    (throw (IllegalArgumentException. (format "%s is not a PDF file" pdf-file)))))
