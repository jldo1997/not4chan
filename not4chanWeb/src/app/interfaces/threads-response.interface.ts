import { thread } from "../model/thread.model";

export interface ThreadResponse {
    rows: thread[];
    count: number;
}