(ns polylith.clj.core.autocomplete.core)

(defn with-val [candidate value]
  (cond
    (map? value) (cond-> (merge candidate value))
    (keyword? value) (assoc candidate :top-group-id value)
    (string? value) (assoc candidate :parsed-value value)
    (integer? value) (assoc candidate :order value)
    (boolean? value) (assoc candidate :stay? value)
    (sequential? value) (assoc candidate :candidates (vec value))
    :else (throw (Exception. (str "Unknown type for value: " value)))))
