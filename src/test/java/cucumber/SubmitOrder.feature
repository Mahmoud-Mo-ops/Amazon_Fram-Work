@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file

  Background: 
    Given I landed on the Ecommerce Page

  @tag2
  Scenario Outline: Positive Test of Submitting the order 
    Given logged in with Username <email> and password <password>
    When search with product <ProductName> in the search Field
    And find subProduct <subProductName> 
    And add subproduct <subProductName> to cart
    And click on cartButton
    And check number Of Products
    And proceed To Checkout
    And select the Country
    And enter your shipping address with fullname <fullname>, phone number <phoneNumberText>, address <addressText>, building name <buildingNameText>, city <addressCityText>, district <addressDistrictText>, state <addressStateText>, governorate <governorateText>, and landmark <landmarkText>
    And submit shipping address
    Then check number of added orders

    Examples: 
      | email                     | password       | ProductName | subProductName | fullname | phoneNumberText | addressText | buildingNameText | addressCityText | addressDistrictText | addressStateText | governorateText | landmarkText    |
      | mahmoudeid1840@gmail.com  | Berlin@1234567 | SAMSUNG     | Galaxy Buds    | mo       | 01030568889     | gehan kfs   | njosw            | elfath hospital       | 61,cv               | gehan kfs        | kfs             | elfath hospital |
