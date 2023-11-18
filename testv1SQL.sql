use a02;

create view Conference as Select Name, EvDate as Date from Event, EventConference  where EventConference.EventID=ID;
Create View Journal as Select Name, MAX(ActivityDate) as Date from Event, ActivityHappens, EventJournal  where ActivityHappens.EventID=ID and EventJournal.EventID=ID Group By ID;
Create View Book as Select Name, MAX(ActivityDate) as Date from Event, ActivityHappens, EventBook where ActivityHappens.EventID=ID and EventBook.EventID=ID Group By ID;
select Name, Date as "Date of Event" from Conference, Journal, Book;