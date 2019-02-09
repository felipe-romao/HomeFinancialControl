package com.example.g508029.homefinancialcontrol;

import com.example.g508029.homefinancialcontrol.model.CategoryGrouped;
import com.example.g508029.homefinancialcontrol.model.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CategoryGroupedListGenerator {
    private List<Transaction> transactions;
    private HashMap<String, CategoryGrouped> categoryGroupedHashMap = new HashMap<>();

    public CategoryGroupedListGenerator(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<CategoryGrouped> generate(){
        for (Transaction transaction : this.transactions){
            if(!this.categoryGroupedHashMap.containsKey(transaction.getCategory())){
                this.categoryGroupedHashMap.put(transaction.getCategory(), new CategoryGrouped(transaction.getCategory()));
            }
            CategoryGrouped categoryGrouped = this.categoryGroupedHashMap.get(transaction.getCategory());
            categoryGrouped.increaseCount();
            categoryGrouped.addValue(transaction.getValue());
        }
        return new ArrayList<CategoryGrouped>(this.categoryGroupedHashMap.values());
    }
}
