
**Example:** METS example of the mandatory structural map

```xml
<mets:structMap ID="uuid-1465D250-0A24-4714-9555-5C1211722FB8" TYPE="PHYSICAL" LABEL="CSIP">
  <mets:div ID="uuid-638362BC-65D9-4DA7-9457-5156B3965A18" LABEL="uuid-4422c185-5407-4918-83b1-7abfa77de182">
    <mets:div ID="uuid-A4E1C5B6-CD9B-43EF-8F0C-3FD3AB688F81" LABEL="Metadata" ADMID="uuid-9124DA4D-3736-4F69-8355-EB79A22E943F uuid-48C18DD8-2561-4315-AC39-F941CBB138B3" DMDID="uuid-906F4F12-BA52-4779-AE2C-178F9206111F">
    </mets:div>
    <mets:div ID="uuid-4ACDC6F3-8A36-4A00-A85F-84A56415E86I" LABEL="Documentation">
      <mets:fptr FILEID="uuid-4ACDC6F3-8A36-4A00-A85F-84A56415E86H">
      </mets:fptr>
    </mets:div>
    <mets:div ID="uuid-26757DC2-4C0F-4431-85B5-5943D1AB5CA3" LABEL="Schemas">
      <mets:fptr FILEID="uuid-4ACDC6F3-8A36-4A00-A85F-84A56415E86F">
      </mets:fptr>
    </mets:div>
    <mets:div ID="uuid-35CB3341-D731-4AC3-9622-DB8901CD6736" LABEL="Representations">
      <mets:fptr FILEID="uuid-4ACDC6F3-8A36-4A00-A85F-84A56415E86G">
      </mets:fptr>
    </mets:div>
  </mets:div>
</mets:structMap>
```


**Example:** METS example of the mandatory structural map including representations

```xml
<mets:structMap ID="uuid-1465D250-0A24-4714-9555-5C1211722FB8" TYPE="PHYSICAL" LABEL="CSIP">
  <mets:div ID="uuid-638362BC-65D9-4DA7-9457-5156B3965A18" LABEL="uuid-4422c185-5407-4918-83b1-7abfa77de182">
    <mets:div ID="uuid-A4E1C5B6-CD9B-43EF-8F0C-3FD3AB688F81" LABEL="Metadata" ADMID="uuid-9124DA4D-3736-4F69-8355-EB79A22E943F uuid-48C18DD8-2561-4315-AC39-F941CBB138B3 uuid-9124DA4D-3736-4F69-8355-EB79A22E943G uuid-48C18DD8-2561-4315-AC39-F941CBB138B4" DMDID="uuid-906F4F12-BA52-4779-AE2C-178F9206111F">
    </mets:div>
    <mets:div ID="uuid-26757DC2-4C0F-4431-85B5-5943D1AB5CA3" LABEL="Schemas">
      <mets:fptr FILEID="uuid-4ACDC6F3-8A36-4A00-A85F-84A56415E86F">
      </mets:fptr>
    </mets:div>
    <mets:div ID="uuid-35CB3341-D731-4AC3-9622-DB8901CD6737" LABEL="Representations/preingest">
      <mets:mptr LOCTYPE="URL" type="simple" href="representations/preingest/METS.xml" title="uuid-5811D494-6045-4741-924C-A1CFA340C277">
      </mets:mptr>
    </mets:div>
    <mets:div ID="uuid-35CB3341-D731-4AC3-9622-DB8901CD6736" LABEL="Representations/submission">
      <mets:mptr LOCTYPE="URL" type="simple" href="representations/submission/METS.xml" title="uuid-5811D494-6045-4741-924C-A1CFA340C278">
      </mets:mptr>
    </mets:div>
    <mets:div ID="uuid-35CB3341-D731-4AC3-9622-DB8901CD6738" LABEL="Representations/ingest">
      <mets:mptr LOCTYPE="URL" type="simple" href="representations/ingest/METS.xml" title="uuid-5811D494-6045-4741-924C-A1CFA340C279">
      </mets:mptr>
    </mets:div>
  </mets:div>
</mets:structMap>
```

