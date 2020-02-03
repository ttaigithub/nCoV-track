# Decision-making platform for prevention and control of big data of epidemic-[中文](https://github.com/ttaigithub/nCoV-track/blob/master/README.md)
## Description
Because of the high infectivity of new coronaviruses among people, we designed a framework of big data platform for retrospective monitoring and prediction of human-to-human transmission, which can effectively restore the activity trajectory of diagnosed patients, suspected patients and possible contacts during the incubation period, and present it intuitively. We hope it will be helpful for decision making.

Our design is an open-source framework, and the team's ability is limited. So the framework of the telecom data module, the prediction module, information release module(Internet platform) is unfinished. We hope that the entire open source community will be able to complete this platform together. And one day make it Decision-making platform of big data for all kinds of Infectious diseases

We highly valued institutions and patient's privacy protection. So the platform will not transform and share data with others. Each organization must deploy its private platform. In the future, we will add more data privacy protection technologies into the platform framework to achieve high-security protection of users' data.
## Framework functions
### To G/B:

1. Big data visualization(finished): Including a geographical distribution map of patients, trajectory map of patients' activities, distribution map of designated hospitals and distribution map of medical and life supplies;
2. Support decision making(unfinished): Forecast of epidemic development, forecast of economic impact and management of forecast model;
3. Notification(unfinished): Information reporting and sharing among vertically parallel institutions, large-scale or point-to-point accurate notification, information release on various public social platforms, and information template management;
4. Platform, patient privacy security protection (can be authorized to use for free): Internal data encryption and isolation, privacy computing supports data analysis. For example, the patient data is encrypted and opened for analysis and research by domestic medical institutions and special data encrypted and sharing for global doctors to solve.
### To C:
1.Medical material support: Provider of maks,alcholo and medicine.Information needs to be entered by hand
2.Updata e-commerce platform information.
3.List of designated hospital.
4.Information of highway closure
### Management platform
Information input and update,multi-agency data linkage sharing interface, etc.
### Unfinished function
Since Tong Tai Tech's emergency response team are all technical personnel in the field of data privacy protection, they are not very good at some fields such as big data processing, visualization optimization, and prediction model. Therefore, we expect 1. Optimize the structure of the platform2. Add epidemic prediction models, which will play a more critical role in data support decisions of the platform;3. Better optimize the current big data visualization to achieve a more beautiful and concise effect.
## Disclaimer
Copyright: Tong Tai Tech's emergency response team, as the resource organizer, all the resources and information displayed so far come from the public information on the Internet, including but not limited to personal road maps, hospital information, highway closure information, mask, and other medical resources and sources. Copyright and data ownership belong to the original owner.

Note: all data provided by this platform are from the network and are only used for the function display of the platform. All information related to the epidemic is subject to the information officially released by the country. Due to the continuous update of relevant epidemic information, all resources and data are for reference only, and the platform shall not assume any responsibility. Any use for commercial purposes shall not be permitted, otherwise, you shall bear all consequences. We provide collation and functional applications but shall not be legally responsible for any resources.


The platform shall not be responsible for any adverse consequences caused by the use of the data collected by the platform. Please keep this information when reposting.

Thank you to all who have expressed concern about the outbreak of the new coronavirus. We will try our best to provide corresponding technical support and resources. At the same time, I beg your understanding, because the production time is in a hurry, please timely point out the shortcomings and a lot of understanding. 


2020年1月31日
Tong Tai Tech's emergency response team
## Code:

NCoV virus tracking system server side：[nCoV-Track-backend](https://github.com/ttaigithub/nCoV-track/tree/master/nCoV-Track-backend)

NCoV virus tracking system web side：[nCoV-Track-fontend](https://github.com/ttaigithub/nCoV-track/tree/master/nCoV-Track-fontend)

Due to the sensitive data interface, to avoid unnecessary panic, we temporarily do not provide input interfaces for activity data of confirmed and suspected patients. We will provide the remaining modules and interfaces free of charge and provide all follow-up support only when the government or epidemic prevention department is using them.