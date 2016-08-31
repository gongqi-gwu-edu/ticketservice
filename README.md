# Ticket Service (Draft v0.1)
  Author: Qi Gong
  
  Email: gongqi@gwu.edu
  
  GitHub Account: gongqi-gwu-edu

# Requirement: 
  Implement a simple ticket service that facilitates the discovery, temporary hold, and final reservation of seats within a high-demand performance venue.
 
# Functions: 
  1. Find the number of seats available within the venue, optionally by seating level (Note: available seats are seats that are neither held nor reserved.)
  
  Function Signature: int numSeatsAvailable(Optional<Integer> venueLevel);
 
  2. Find and hold the best available seats on behalf of a customer, potentially limited to specific levels (Note: each ticket hold should expire within a set number of seconds.)
  
  Function Signature: SeatHold findAndHoldSeats(int numSeats, Optional<Integer> minLevel, Optional<Integer> maxLevel, String customerEmail)
 
  3. Reserve and commit a specific group of held seats for a customer
  
  Function Signature: String reserveSeats(int seatHoldId, String customerEmail)
 
# Assumptions:
  1. User can hold a seat within 60 second.
  
  2. User can only specify level of seat.
  
  3. User can hold more than one seat.
  
  4. User may hold seats in different level if user gave different value for minLevel and maxLevel.
  
  5. User cannot hold seats if there is no enough seats.
  
# Entity Design:
  Three entities are defined.
  1. Level, which specifies seats info in different levels, including levelId, levelName, price, rowNumber and seatNumberInRow
  
  2. SeatHold, which specifies user's application/operation info, including seatHoldId, seatNumber, minLevelId, maxLevelId, customerEmail, confirmationCode, holdTime, reservationTime and seatHoldDetails (Each SeatHold object may contain more than 1 SeatHoldDetail object)
  
  3. SeatHoldDetail, which specifies seats arrangement, including id, level and seatNumber (Each SeatHoldDetail object has and can only have 1 Level object)
 
# Interface Design
  Refer to interface TicketService
  
# Page Design
  1. index page
  2. level summary page and detail page
  3. seat hold summary page and detail page
  4. seat hold form page
  
# Development/Deployment Reference
  JDK 1.8, spring-boot 1.2.4, bootstrap 3.3.4, Maven 3.3, JUnit 4
  
# How to build and do unit test
  1. Create walmart directory under you home directory, and enter this directory

  2. Clone project for GitHub
  
  git clone https://github.com/gongqi-gwu-edu/ticketservice.git

  3. Enter ticketservice directory
  
  4. Run unit test
  
  mvn test
  
  5. Run application
  
  mvn spring-boot:run
  
  6. open http://localhost:8080/ in web browser
  
# How to use the ticket service application
  1. Click Tickets link to find available seats number
  2. Click View link after each level to find available seats number of each level
  3. Click Hold Ticket link to open ticker hold form
  4. Fill in application then click Submit
  5. Click Orders link to view your hold request
  6. Click Reserve link to reverse seats or click Delete to remove hold requests
  7. Make sure reverse seats within 60 second.
  
  (8. You can choose a better expire time by changing seathold.expiretime.insecond value in src\main\resources\application.properties)

# If you have any questions, please do not hesitate to send me an email at gongqi@gwu.edu
