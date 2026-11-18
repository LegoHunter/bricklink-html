# bricklink-html

BrickLink HTML scraping and website-form client (Java library).

## Status: legacy, maintenance only

**The website login in this library no longer works.** BrickLink moved its sign-in to the LEGO
Account (username, password and an emailed code), and that flow cannot be driven by this library's
HTTP client. Everything here that needs a signed-in BrickLink website session therefore fails:
uploading a lot's custom image, editing a lot through the website forms, and the wanted-list pages.

What replaces it:

| Need | Use instead |
|---|---|
| Signed-in website work (custom lot image, Extended Description) | [`bricklink-web-gateway`](https://github.com/LegoHunter/bricklink-web-gateway): a service that holds a signed-in browser session and offers task-level REST endpoints |
| Catalog and price data that was scraped from pages | `bricklink-ajax` and `bricklink-rest` |
| Inventory, orders and other store data | `bricklink-rest` (the official BrickLink REST API) |

Rules for this repository:

- Do not add new functionality here.
- No code is being removed: `lego-command` (the live PROD command-line tool), `bricklink-reporting`
  and `bricklink-api-example` still compile against this library.
- Fixes are limited to what those consumers need to keep building.
