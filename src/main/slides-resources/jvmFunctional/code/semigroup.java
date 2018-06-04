interface SemiGroup {
    SemiGroup concat(SemiGroup other);
    // this.concat(x.concat(y)) = this.concat(x).concat(y)
}