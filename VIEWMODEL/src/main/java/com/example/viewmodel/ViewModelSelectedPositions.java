package com.example.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.HashSet;
import java.util.Set;

public class ViewModelSelectedPositions extends ViewModel {
    private MutableLiveData<Set<Integer>> selectedPositions = new MutableLiveData<>(new HashSet<>()); // Initial empty set

    // Get the selected positions
    public LiveData<Set<Integer>> getSelectedPositions() {
        return selectedPositions;
    }

    // Add a selected position to the set
    public void addSelectedPosition(int position) {
        Set<Integer> currentPositions = selectedPositions.getValue();
        if (currentPositions != null) {
            currentPositions.add(position);
            selectedPositions.setValue(currentPositions); // Update LiveData
        }
    }

    // Remove a selected position from the set
    public void removeSelectedPosition(int position) {
        Set<Integer> currentPositions = selectedPositions.getValue();
        if (currentPositions != null) {
            currentPositions.remove(position);
            selectedPositions.setValue(currentPositions); // Update LiveData
        }
    }

    // Clear all selected positions
    public void clearSelectedPositions() {
        selectedPositions.setValue(new HashSet<>()); // Clear the set
    }
}
