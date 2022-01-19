(ns polylith.clj.core.autocomplete.interface
  (:require [polylith.clj.core.autocomplete.core :as core]))

(defn candidate [value display parsed-value type args]
  (core/candidate value display parsed-value type args))

(defn optional []
  (core/optional))

(defn group [id]
  (core/group id))

(defn in-group [group-id candidate]
  (core/in-group group-id candidate))

(defn function [f]
  (core/function f))

(defn single-txt [value & args]
  (core/single-txt value args))

(defn flag-explicit [value group-id & values]
  (core/flag-explicit value group-id values))

(defn flag [value group-id & values]
  (core/flag value group-id values))

(defn group-arg [value group-id param & values]
  (core/group-arg value group-id param values))

(defn multi-arg [group-id param & values]
  (core/multi-arg group-id param values))

(defn fn-comma-arg [value group-id param function & values]
  (core/fn-comma-arg value group-id param function values))

(defn fn-explorer [value group-id select-fn]
  (core/fn-explorer value group-id select-fn))

(defn multi-fn [value & args]
  (core/multi-fn value args))

(defn multi-param [value & args]
  (core/multi-param value args))
