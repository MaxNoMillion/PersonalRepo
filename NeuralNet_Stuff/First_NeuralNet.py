################################################
## Name: Andrew Steen
## Date: 03-12-23
## Discription: First Neural Network using libraries
################################################

from PIL import Image



im = Image.open('E:\Co-User\Documents\__LaTech__\Computer Science\__Repos__\PersonalRepo\NeuralNet_Stuff\Image_Data\test1.gif') # Can be many different formats.
pix = im.load()
print im.size  # Get the width and hight of the image for iterating over
print pix[x,y]  # Get the RGBA Value of the a pixel of an image
pix[x,y] = value  # Set the RGBA Value of the image (tuple)
im.save('alive_parrot.png')  # Save the modified pixels as .png