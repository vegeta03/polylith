(ns polylith.clj.core.shell.interface.creator
  (:require [polylith.clj.core.shell.candidate.creator :as creator]))

(defn candidate [value display parsed-value type args]
  (creator/candidate value display parsed-value type args))

(defn optional []
  (creator/optional))

(defn group [id]
  (creator/group id))

(defn in-group [group-id candidate]
  (creator/in-group group-id candidate))

(defn function [f]
  (creator/function f))

(defn single-txt [value & args]
  (creator/single-txt value args))

(defn flag-explicit [value group-id & values]
  (creator/flag-explicit value group-id values))

(defn flag [value group-id & values]
  (creator/flag value group-id values))

(defn group-arg [value group-id param & values]
  (creator/group-arg value group-id param values))

(defn multi-arg [group-id param & values]
  (creator/multi-arg group-id param values))

(defn fn-comma-arg [value group-id param function & values]
  (creator/fn-comma-arg value group-id param function values))

(defn fn-explorer [value group-id select-fn]
  (creator/fn-explorer value group-id select-fn))

(defn fn-explorer-child [value entity color-mode group select-fn]
  (creator/fn-explorer-child value entity color-mode group select-fn))

(defn multi-fn [value & args]
  (creator/multi-fn value args))

(defn multi-param [value & args]
  (creator/multi-param value args))
