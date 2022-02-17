# Subnet Calculator

Android app which will allow the user to calculate an IPv4 subnet range. This was one of the final assignments of the module COM502 - Internet and Mobile Application Development in Wrexham Glyndŵr University (United Kingdom).

### Difficulties and how I solved them

One of the difficulties I have been through was being able to make the calculator work for subnet masks that are not like the standard ones. Despite that, I could solve it by checking what kind of subnet mask is (concerning the standard ones) and doing the calculations based on that.

Besides, another difficulty I found was avoiding incorrect calculations, such as calculating the subnet masks of an IP address of the class ‘C’ will a subnet mask of class ‘A’, for instance. This kind of introduced data that are not correct needed to be corrected, so I solved it by checking if the data matches before displaying the
subnet mask list. In the following screenshots, you can see how it is not possible to do it anymore.

One last difficulty would be joining the scroll movement of the three ListView elements. I decided to do it in separate ListViews because, knowing it was more difficult, I wanted to try it that way to make the display clearer.
