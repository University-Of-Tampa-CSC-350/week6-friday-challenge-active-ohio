# FC 006
This challenge can be worked on in pairs of 2 and the focus for this challenge is on the topics we covered this week:
1. Fetching API's
2. Loading State
3. Retrofit

## Github Classrooms
We will be using Github classroom to do in-class Friday Challenges.
You can make as many commits and as many pushes as needed to the main branch on your forked copy of the repo.
The notes about commits are still relevant here:
To be consistent, use the same styling for commit messages that was given in the Project I:
- PREFIX – Short description of the change
  A detailed description can be added to the commit in the long description, if needed.
  The following are the possible options for [Prefix]
- [FEAT] - For new features or major additions to the project.
  FEAT - Added button click-ability feature 
- [FIX] - For bug fixes, corrections, or revisions to the code.
  FIX - Corrected navigation bar alignment on mobile devices
- [STYLE] - For stylistic changes such as formatting, CSS modifications, or minor visual updates.
  STYLE - Updated color scheme for better contrast
- [DOCS] - For changes or additions to the documentation, including README files and comments in the code.
  DOCS - Added project description and setup instructions to README
- [SECURITY] - For changes related to improving the security of the website.
  SECURITY - Implemented input validation for contact form
- [REFACTOR] - For code refactoring that doesn’t change functionality but improves code quality or organization.
  REFACTOR - Organized attributes for button components files in Home layout.
- [TEST] - For adding tests or making changes to the testing suite.
  TEST - Added validation tests for login form input

### 5. Submitting your work
Once, you are sure that all the work is completed, go through the following steps for submission.
Push all your work onto the main branch. **Only the main branch** will be considered for grading.

## Project description
Your application will function as a small Asteroid Monitoring Console used by mission control.
Before you start working on the activity, make sure to create an API key from this website - https://www.api.nasa.gov.

Scientists track Near-Earth Objects (NEOs) - asteroids that pass close to Earth’s orbit. 
Your application must connect to a NASA web service and retrieve information about these objects one at a time.

The goal of this challenge is to build a simple interface that:
* Sends a request to the NASA NEO API 
* Retrieves asteroid data 
* Displays important information about one asteroid (at a time)
* Extra points for creating visually appealing frontend.
* Handles loading and error states

## API Endpoint 
API Endpoint for https://api.nasa.gov/neo/rest/v1/neo/browse?api_key=DEMO_KEY 
Make sure to replace the DEMO_KEY with your actual APIKEY
### Task 1: Adding required Dependencies
Add the required dependencies for:
* Retrofit 
* Gson converter 
* Coroutines

These will allow your application to retrieve data from the NASA API.

### Task 2: Create Data Models

Create Kotlin data classes representing the asteroid data, depending on the information present in the JSON file retrieved from the API access.
All the information present in the API need not be shown, it's up to your group to decide which 
information can be shown and which can be omitted.

### Task 3 – Create the API Interface

Define a Retrofit interface to retrieve asteroid data. Refer to this week slides to get an idea on how to create 
and API interface.

### Task 4 – Build the Asteroid Monitor Screen

Your layout must contain:

Button: Scan for Asteroids
ProgressBar: Displayed while the API request is running (Simulate a low latency network, this can be done in the 
Android studio emulator)
TextViews: Display asteroid information.

### Task 5 – Display Asteroid Information

After the API response is received, display at least:
* Asteroid Name
* Whether it is Potentially Hazardous
* Distance from Earth (km)

Only one astreiod per screen.

### Task 6 – Handle Asynchronous State

Your UI must handle three states:

Scanning Space: Show a ProgressBar.
Asteroid Detected: Display asteroid information.
Scan Failed: Display an error message such as: “Mission Control lost asteroid tracking data.”

### Task 7 – Use ViewModel

The API request must be handled inside a ViewModel.

Fragments should only:
* Trigger the scan
* Observe the state
* Update the UI

Make sure to not include any network calls should not occur inside fragments.

### Bonus Points
+2 points for the best UI!
