# medical_clinic
Medical Clinic

In my clinic project we currently have 4 medical staff and 4 reception staff. Currently, the maximum possible number of medical staff that we can enrol is 200. At the moment, we do not need any more staff.  
When starting the programme, we need to call up 3 lists - medical staff, reception and patients. This is because our programme is based on the terminal only. Each person has an id assigned to them. Medical staff from number 1 to 200, reception from 200 and patients from 1000.  

To begin with, our programme asks for the id, because a medical employee and a receptionist have different capabilities - a medical can open a patient's chart, enter a note (creating a plik.txt ) to the appointment and write the medication, while the receptionist can book / cancel a visit, register a new / delete a patient and calculate the cost of the visit. 
Employees and patients belong to the abstract class Person, which contains two lists - allEmployees (a combination of medical and reception staff lists) and allPatients. 
Each person has an id (id assignment is separate in each class), a first name and a last name. The classes for medical worker and receptionist are separate so that they have different id assignments. Both the employee class and the patient class are based on HashMaps. The classes also have their own lists that subscribe to their classes. 

As the receptionist can independently enrol new patients, the number of can grow. I have therefore included the possibility of sorting individuals and for Sorter class for this. 
The class CennikBazowy (PriceBase) was created to calculate the cost of a visit that can be made by a reception employee. We have 5 services on offer - a first-time visit, a subsequent visit, ultrasound, morphology and other blood tests.  

The Lek (Medication) class was created so that the doctor can assign medication to the patient. We provide the name of the medicine, the weight of the medicine and the quantity.
