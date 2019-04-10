import { category } from "../model/category.model";

export interface CategoriesResponse {
    rows: category[];
    count: number;
}