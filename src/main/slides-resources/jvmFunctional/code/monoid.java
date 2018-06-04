interface Monoid extends SemiGroup {
    static Monoid neutral = ???;
    // monoid.concat(neutral) = monoid, neutral.concat(monoid) = monoid
}