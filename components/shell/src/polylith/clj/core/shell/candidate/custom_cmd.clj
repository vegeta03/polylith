(ns polylith.clj.core.shell.candidate.custom-cmd
  (:require [polylith.clj.core.util.interface :as util]))

(defn spec [[_ cmd-ns] workspace]
  ((util/fn-var cmd-ns "spec") workspace))

(defn specs [workspace]
  (mapv #(spec % workspace)
        (-> workspace :settings :commands)))
