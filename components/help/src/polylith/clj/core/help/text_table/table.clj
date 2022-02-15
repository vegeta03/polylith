(ns polylith.clj.core.help.text-table.table
  (:require [polylith.clj.core.text-table.interface :as text-table]))

(defn command-cells [index {:keys [command description]}]
  (let [row (inc index)]
    [(text-table/cell 1 row command)
     (text-table/cell 2 row "  ")
     (text-table/cell 3 row description)]))

(defn table [color-mode commands]
  (let [cells (mapcat identity
                      (map-indexed command-cells commands))]
    (text-table/table "    " color-mode cells)))

(defn print-table [color-mode commands]
  (text-table/print-table (table color-mode commands)))
