interface Monoid extends SemiGroup {
    empty: Monoid;
    // monoid.concat(empty) = monoid, empty.concat(monoid) = monoid
}