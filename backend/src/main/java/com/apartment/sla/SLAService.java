package com.apartment.sla;

import java.util.UUID;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

// This import will fail until Module 2 is merged. 
// We are assuming the class name "Complaint" will exist.
import com.apartment.complaint.Complaint; 

public class SLAService {
    
    private ConcurrentHashMap<UUID, Complaint> sharedComplaintStore;
    
    // SLA Rule: Complaints must be resolved within 24 hours
    private static final long SLA_LIMIT_HOURS = 24;

    public SLAService(ConcurrentHashMap<UUID, Complaint> store) {
        this.sharedComplaintStore = store;
    }

    // This method checks all complaints for breaches
    public void checkSlaBreaches() {
        System.out.println("--- Running SLA Scanner ---");
        
        if (sharedComplaintStore == null || sharedComplaintStore.isEmpty()) {
            System.out.println("No complaints to check.");
            return;
        }

        for (Map.Entry<UUID, Complaint> entry : sharedComplaintStore.entrySet()) {
            Complaint complaint = entry.getValue();
            
            // Logic: If (Now - CreatedDate) > 24 hours AND Status is NOT Resolved
            long hoursOpen = ChronoUnit.HOURS.between(complaint.getCreatedAt(), LocalDateTime.now());

            // assuming "isResolved()" is a method Ambika will add
            if (hoursOpen > SLA_LIMIT_HOURS && !complaint.isResolved()) {
                System.out.println("ALERT: SLA Breach for Complaint ID: " + complaint.getId());
                // Future Step: Trigger NotificationService here
            }
        }
    }
}