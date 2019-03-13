import { user } from "../model/user";

export interface UserResponse {
    rows: user[];
    count: number;
}