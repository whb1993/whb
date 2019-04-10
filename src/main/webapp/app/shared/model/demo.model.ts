export interface IDemo {
    id?: number;
    regionName?: string;
    regionTime?: string;
}

export class Demo implements IDemo {
    constructor(public id?: number, public regionName?: string, public regionTime?: string) {}
}
